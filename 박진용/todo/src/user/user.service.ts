import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { UserEntity } from 'src/entity/user.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from './dto/user.dto';
import { LoginUserDto } from './dto/loginuser.dto';
import * as bcrypt from 'bcrypt';
@Injectable()
export class UserService {
  constructor(
    @InjectRepository(UserEntity)
    public readonly userRepository: Repository<UserEntity>,
  ) {}

  public async addUser(user: User): Promise<UserEntity> {
    const newUser = this.userRepository.create(user);
    await this.userRepository.save(newUser);
    return newUser;
  }

  public async isUniqueEmail(email: string) {
    const user = await this.userRepository.find({ where: { email } });
    return user.length === 0 ? true : false;
  }

  public async findByPayload({ username }: any): Promise<User> {
    return await this.userRepository.findOne({ where: { username } });
  }

  public async getLogin({ email, password }: LoginUserDto) {
    const user = await this.userRepository.findOne({ where: { email } });
    if (!user) {
      throw new HttpException(
        '존재하지 않는 이메일 입니다.',
        HttpStatus.UNAUTHORIZED,
      );
    }
    const isValidLogin = bcrypt.compareSync(password, user.password);
    if (!isValidLogin) {
      throw new HttpException('Invalid Login', HttpStatus.UNAUTHORIZED);
    }

    return new User(user);
  }
}

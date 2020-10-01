import { Injectable } from '@nestjs/common';
import { UserEntity } from 'src/entity/user.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from './dto/user.dto';
import { LoginUserDto } from './dto/loginuser.dto';

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

  public async getUserInfoByEmail(email): Promise<UserEntity[]> {
    return await this.userRepository.find({ where: { email: email } });
  }
  public async isUniqueEmail(email: string) {
    const user = await this.userRepository.find({ where: { email: email } });
    return user ? false : true;
  }
  public async getLoginInfo({ email, password }: LoginUserDto) {}
}

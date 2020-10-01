import { Injectable } from '@nestjs/common';
import { UserEntity } from 'src/entity/user.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from '../entity/dto/user.dto';
import { addUserDto } from 'src/entity/dto/adduser.dto';

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

  public async getAllUser(): Promise<UserEntity[]> {
    return await this.userRepository.find();
  }
}

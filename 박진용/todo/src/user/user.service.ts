import { Injectable } from '@nestjs/common';
import { UserEntity } from 'src/entity/user.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(UserEntity)
    public readonly userRepository: Repository<UserEntity>,
  ) {}

  public async getAllUser(): Promise<UserEntity[]> {
    return await this.userRepository.find();
  }
}

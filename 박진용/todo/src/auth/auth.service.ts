import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { InjectRepository } from '@nestjs/typeorm';
import { UserEntity } from 'src/entity/user.entity';
import { LoginUserDto } from 'src/user/dto/loginuser.dto';
import { UserService } from 'src/user/user.service';
import { Repository } from 'typeorm';
import { User } from '../user/dto/user.dto';
import { JwtPayload } from './jwt.strategy';

@Injectable()
export class AuthService {
  constructor(
    private readonly userService: UserService,
    private readonly jwtService: JwtService,
    @InjectRepository(UserEntity)
    public readonly userRepository: Repository<UserEntity>,
  ) {}

  public async login(loginUserDto: LoginUserDto) {
    const user = await this.userService.getLogin(loginUserDto);
    const token = this._createToken(user);

    return { username: user.username, token };
  }

  private _createToken({ username }: User): any {
    const user: JwtPayload = { username };
    const accessToken = this.jwtService.sign(user);
    return accessToken;
  }
  public async validateUser(payload: JwtPayload): Promise<User> {
    const user = await this.userService.findByPayload(payload);
    if (!user) {
      throw new HttpException('Invalid token', HttpStatus.UNAUTHORIZED);
    }
    return user;
  }
}

import { Body, Controller, Injectable, Post } from '@nestjs/common';
import { UserService } from 'src/user/user.service';
import { LoginUserDto } from '../user/dto/loginuser.dto';
import { AuthService } from './auth.service';

@Controller('auth')
export class AuthController {
  constructor(
    private readonly userService: UserService,
    private readonly authService: AuthService,
  ) {}
  @Post('login')
  public async login(@Body() loginUserDto: LoginUserDto) {
    return await this.authService.login(loginUserDto);
  }
}

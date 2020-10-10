import { Body, Controller, HttpStatus, Post, Req, Res } from '@nestjs/common';
import { Response, Request } from 'express';
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
  public async login(
    @Body() loginUserDto: LoginUserDto,
    @Req() request: Request,
    @Res() response: Response,
  ) {
    const cookie = await this.authService.login(loginUserDto);
    response.cookie('auth', cookie.token.Authentication);
    response.status(HttpStatus.OK).send('ok');
    return;
  }
}

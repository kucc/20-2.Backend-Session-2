import { Controller, Post, Get, Body } from '@nestjs/common';

// ~~/user
@Controller('user')
export class UserController {
  @Post()
  addUser(
    @Body('email') userEmail: string,
    @Body('password') userPassword: string,
    @Body('username') username: string,
    @Body('profile_image') userImg: string,
  ): any {}
}

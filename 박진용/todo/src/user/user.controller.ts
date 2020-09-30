import { Controller, Post, Get, Body } from '@nestjs/common';
import { UserService } from './user.service';
// ~~/user
@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Get()
  public async getAllUser() {
    return await this.userService.getAllUser();
  }
  @Post()
  public addUser(
    @Body('email') userEmail: string,
    @Body('password') userPassword: string,
    @Body('username') username: string,
    @Body('profile_image') userImg: string,
  ): any {}
}

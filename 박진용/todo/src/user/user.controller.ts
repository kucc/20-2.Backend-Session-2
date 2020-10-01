import { Controller, Post, Get, Body } from '@nestjs/common';
import { UserService } from './user.service';
import { addUserDto } from '../entity/dto/adduser.dto';
// ~~/user
@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Get()
  public async getAllUser() {
    return await this.userService.getAllUser();
  }
  @Post()
  public async addUser(@Body() addUserDto: addUserDto): Promise<any> {
    return await this.userService.addUser(addUserDto);
  }
}

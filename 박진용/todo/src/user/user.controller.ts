import { Controller, Post, Get, Body } from '@nestjs/common';
import { UserService } from './user.service';
import { addUserDto } from './dto/adduser.dto';
import { User } from './dto/user.dto';
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
    const newUser = new User(addUserDto);
    return await this.userService.addUser(newUser);
  }
}

import { Controller, Post, Get, Body, Res, HttpStatus } from '@nestjs/common';
import { UserService } from './user.service';
import { addUserDto } from './dto/adduser.dto';
import { User } from './dto/user.dto';
import { AuthService } from 'src/auth/auth.service';
import { Response } from 'express';
@Controller('user')
export class UserController {
  constructor(
    private readonly authService: AuthService,
    private readonly userService: UserService,
  ) {}

  @Get()
  public async getAllUser() {
    return await this.userService.getAllUser();
  }
  @Post()
  public async addUser(
    @Res() response: Response,
    @Body() addUserDto: addUserDto,
  ): Promise<any> {
    const newUser = new User(addUserDto);
    const isUnique = await this.authService.isUniqueEmail(newUser.email);
    if (!isUnique) {
      response
        .status(HttpStatus.BAD_REQUEST)
        .json({ result: '이미 존재하는 이메일' });
      return;
    }

    return await this.userService.addUser(newUser);
  }
}

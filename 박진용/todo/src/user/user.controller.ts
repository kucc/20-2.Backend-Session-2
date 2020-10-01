import {
  Controller,
  Post,
  Get,
  Body,
  Res,
  HttpStatus,
  Param,
} from '@nestjs/common';
import { UserService } from './user.service';
import { addUserDto } from './dto/adduser.dto';
import { User } from './dto/user.dto';
import { AuthService } from 'src/auth/auth.service';
import { Response } from 'express';
@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post()
  public async addUser(
    @Res() response: Response,
    @Body() addUserDto: addUserDto,
  ): Promise<any> {
    //service 부분으로 refactor하기
    const newUser = new User(addUserDto);
    const isUnique = await this.userService.isUniqueEmail(newUser.email);
    if (!isUnique) {
      response
        .status(HttpStatus.BAD_REQUEST)
        .json({ result: '이미 존재하는 이메일' });
      return;
    }
    await this.userService.addUser(newUser);
    response.status(HttpStatus.OK).json({ result: 'success!' });
  }
}

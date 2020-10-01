import { addUserDto } from './adduser.dto';
import * as bcrypt from 'bcrypt';
export class User {
  public constructor(addUserRequest: addUserDto) {
    this.email = addUserRequest.email;
    this.password = User.getHashPassword(addUserRequest.password);
    this.username = addUserRequest.username;
    this.profile_image = addUserRequest.profile_image;
  }
  //static으로 하는게 맞나??
  static getHashPassword(password: string): string {
    const salt = bcrypt.genSaltSync(10);
    const hashPassword = bcrypt.hashSync(password, salt);
    console.log(hashPassword);
    return hashPassword;
  }

  email: string;
  password: string;
  username: string;
  profile_image: string;
}

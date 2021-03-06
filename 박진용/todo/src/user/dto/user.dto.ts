import { addUserDto } from './adduser.dto';
export class User {
  public constructor(addUserRequest: addUserDto) {
    this.email = addUserRequest.email;
    this.username = addUserRequest.username;
    this.profile_image = addUserRequest.profile_image;
  }

  email: string;
  password: string;
  username: string;
  profile_image: string;
}

import { IsNotEmpty } from 'class-validator';

export class addUserDto {
  @IsNotEmpty()
  email: string;

  @IsNotEmpty()
  password: string;

  @IsNotEmpty()
  username: string;

  profile_image: string;
}

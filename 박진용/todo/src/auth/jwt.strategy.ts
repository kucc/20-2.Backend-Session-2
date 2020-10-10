import {
  HttpException,
  HttpStatus,
  Injectable,
  UnauthorizedException,
} from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { AuthService } from './auth.service';
import { Strategy, VerifiedCallback } from 'passport-jwt';
import { User } from '../user/dto/user.dto';
import * as dotenv from 'dotenv';
import { JwtPayload } from './auth.service';
dotenv.config();

@Injectable()
export class JwtStrategy extends PassportStrategy(Strategy, 'jwt') {
  constructor(private readonly authService: AuthService) {
    super({
      jwtFromRequest: req => {
        let token = null;
        if (req && req.cookies['auth']) token = req.cookies['auth'];
        return token;
      },
      secretOrKey: process.env.JWT_SECRET_KEY,
    });
  }

  async validate(payload: JwtPayload, done: VerifiedCallback) {
    const user = await this.authService.validateUser(payload);
    if (!user) {
      throw new HttpException('Invalid token', HttpStatus.UNAUTHORIZED);
    }
    return user;
  }
}

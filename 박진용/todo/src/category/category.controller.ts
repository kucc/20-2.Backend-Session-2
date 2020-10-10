import { Controller, Get, Req, Res, UseGuards } from '@nestjs/common';
import { CategoryService } from './category.service';
import { Request, Response } from 'express';
import { JwtAuthGuard } from 'src/auth/guards/jwt-auth.guard';
import { UserEntity } from 'src/entity/user.entity';

@Controller('category')
export class CategoryController {
  constructor(private readonly categoryServie: CategoryService) {}

  @UseGuards(JwtAuthGuard)
  @Get()
  public async getCategory(@Req() req, @Res() response: Response) {
    const user: UserEntity = req.user;
    const categories = await this.categoryServie.getCategoryByUser(user.id);
    response.send(categories);
  }
}

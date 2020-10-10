import { Inject, Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { CategoryEntity } from 'src/entity/category.entity';
import { UserHasCategoryEntity } from 'src/entity/user-has-category.entity';
import { User } from 'src/user/dto/user.dto';
import { createQueryBuilder, Repository } from 'typeorm';
import { getCategoryDto } from './dto/getCategory.dto';

@Injectable()
export class CategoryService {
  constructor(
    @InjectRepository(CategoryEntity)
    public readonly CategoryRepository: Repository<CategoryEntity>,
    @InjectRepository(UserHasCategoryEntity)
    public readonly UserHasCategoryRepository: Repository<
      UserHasCategoryEntity
    >,
  ) {}
  public async addCategory() {}
  public async getCategoryByUser(userId: number) {
    const category = this.UserHasCategoryRepository.createQueryBuilder(
      'userHasCategory',
    )
      .leftJoinAndSelect('userHasCategory.category', 'category')
      .where('userId = :userId', { userId })
      .getMany();

    return category;
  }
}

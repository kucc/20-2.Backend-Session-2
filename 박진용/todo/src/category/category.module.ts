import { forwardRef, Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthModule } from 'src/auth/auth.module';
import { CategoryEntity } from 'src/entity/category.entity';
import { UserHasCategoryEntity } from 'src/entity/user-has-category.entity';
import { CategoryController } from './category.controller';
import { CategoryService } from './category.service';

@Module({
  imports: [
    TypeOrmModule.forFeature([CategoryEntity, UserHasCategoryEntity]),
    forwardRef(() => AuthModule),
  ],
  controllers: [CategoryController],
  providers: [CategoryService],
  exports: [CategoryService],
})
export class CategoryModule {}

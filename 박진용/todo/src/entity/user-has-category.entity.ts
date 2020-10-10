import { Entity, Column, ManyToOne } from 'typeorm';
import { UserEntity } from './user.entity';
import { CategoryEntity } from './category.entity';

@Entity('userHasCategory')
export class UserHasCategoryEntity {
  @Column({ type: 'tinyint', nullable: true })
  level: number;

  @ManyToOne(type => UserEntity, { primary: true })
  user: UserEntity;

  @ManyToOne(type => CategoryEntity, { primary: true })
  category: CategoryEntity;
}

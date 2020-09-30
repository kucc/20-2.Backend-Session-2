import { Entity, Column, PrimaryGeneratedColumn, OneToMany } from 'typeorm';
import { TodoEntity } from './todo.entity';
import { UserHasCategoryEntity } from './user-has-category.entity';

@Entity('category')
export class CategoryEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'varchar', nullable: false })
  title: string;

  @OneToMany(
    type => UserHasCategoryEntity,
    userHasCategory => userHasCategory.category,
  )
  userHasCategory: UserHasCategoryEntity[];

  @OneToMany(
    type => TodoEntity,
    todo => todo.category,
  )
  todo: TodoEntity[];
}

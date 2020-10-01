import {
  Entity,
  Column,
  PrimaryGeneratedColumn,
  OneToMany,
  BeforeInsert,
} from 'typeorm';
import { UserHasCategoryEntity } from './user-has-category.entity';
import * as bcrypt from 'bcrypt';
@Entity('users')
export class UserEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'varchar', length: 320, nullable: false })
  email: string;

  @Column({ type: 'varchar', nullable: false })
  password: string;

  @Column({ type: 'varchar', nullable: false })
  username: string;

  @Column({ type: 'text', nullable: true })
  profile_image: string;

  @OneToMany(
    type => UserHasCategoryEntity,
    userHasCategory => userHasCategory.user,
  )
  userHasCategory: UserHasCategoryEntity[];

  @BeforeInsert() async hashPassword() {
    this.password = await bcrypt.hash(this.password, 10);
  }
}

import {
  Entity,
  Column,
  PrimaryGeneratedColumn,
  Timestamp,
  ManyToOne,
} from 'typeorm';
import { CategoryEntity } from './category.entity';
@Entity('todo')
export class TodoEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  content: string;

  @Column({ type: 'tinyint' })
  finished: number;

  @Column({ type: 'timestamp', default: null })
  finished_at: Timestamp;

  @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP' })
  created_at: Timestamp;

  @ManyToOne(
    type => CategoryEntity,
    category => category.todo,
    { nullable: false },
  )
  category: CategoryEntity;
}

import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity('todo')
export class TodoEntity {
  @PrimaryGeneratedColumn()
  id: number;
}

import {Component, OnInit} from '@angular/core';
import {UserTask} from "../../services/manage-users/user-task";

@Component({
  selector: 'app-kanban',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.css']
})
export class KanbanComponent implements OnInit {
  availableTask!: UserTask[];

  selectedTask!: UserTask[];

  draggedTask!: UserTask | null;

  constructor() {

  }


  ngOnInit(): void {
    this.availableTask = [{id: 1, name: "Andrei"}];
    this.selectedTask = [];
  }

  public dragStart(task: UserTask) {
    this.draggedTask = task;
  }

  public drop(event: any) {
    if (this.draggedTask) {
      let draggedProductIndex = this.findIndex(this.draggedTask);
      this.selectedTask = [...this.selectedTask, this.draggedTask];
      this.availableTask = this.availableTask.filter((val, i) => i != draggedProductIndex);
      this.draggedTask = null;
    }
  }

  public dragEnd(event: any) {
    this.draggedTask = null;
  }

  public findIndex(task: UserTask) {
    let index = -1;
    for (let i = 0; i < this.availableTask.length; i++) {
      if (task.id === this.availableTask[i].id) {
        index = i;
        break;
      }
    }
    return index;
  }


}

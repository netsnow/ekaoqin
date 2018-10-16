import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { StudentRoutingModule } from './student-routing.module';
import { StudentListComponent } from './list/student-list.component';


const COMPONENTS = [StudentListComponent];

@NgModule({
  imports: [
    SharedModule,
    StudentRoutingModule
  ],
  declarations: [
    StudentListComponent,
  ],
  entryComponents: COMPONENTS
})
export class StudentModule { }

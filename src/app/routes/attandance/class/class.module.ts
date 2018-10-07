import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { ClassRoutingModule } from './class-routing.module';
import { ClassListComponent } from './list/class-list.component';


const COMPONENTS = [ClassListComponent];

@NgModule({
  imports: [
    SharedModule,
    ClassRoutingModule
  ],
  declarations: [
    ClassListComponent,
  ],
  entryComponents: COMPONENTS
})
export class ClassModule { }

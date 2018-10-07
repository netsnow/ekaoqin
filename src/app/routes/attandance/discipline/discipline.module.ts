import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { DisciplineRoutingModule } from './discipline-routing.module';
import { DisciplineListComponent } from './list/discipline-list.component';


const COMPONENTS = [DisciplineListComponent];

@NgModule({
  imports: [
    SharedModule,
    DisciplineRoutingModule
  ],
  declarations: [
    DisciplineListComponent,
  ],
  entryComponents: COMPONENTS
})
export class DisciplineModule { }

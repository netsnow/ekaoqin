import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DisciplineListComponent } from './list/discipline-list.component';


const routes: Routes = [
  { 
    path: 'list', component: DisciplineListComponent 
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DisciplineRoutingModule { }

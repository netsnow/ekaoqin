import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BackRoomComponent } from './room/back-room.component';
import { BackClassComponent } from './class/back-class.component';
import { BackDetailComponent } from './detail/back-detail.component';

const routes: Routes = [
  { 
    path: 'room', component: BackRoomComponent 
  },
  { 
    path: 'class', component: BackClassComponent 
  },
  { 
    path: 'detail', component: BackDetailComponent 
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackRoutingModule { }

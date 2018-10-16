import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ACLGuard } from '@delon/acl';

const routes: Routes = [
  {
    path: 'user',
    loadChildren: './user/user.module#UserModule',
    //canActivateChild: [ACLGuard],
    //data: { guard: 'ROLE_ADMIN' }
  },
  {
    path: 'class',
    loadChildren: './class/class.module#ClassModule',
    //canActivateChild: [ACLGuard],
    //data: { guard: 'ROLE_ADMIN' }
  },
  {
    path: 'room',
    loadChildren: './room/room.module#RoomModule',
    //canActivateChild: [ACLGuard],
    //data: { guard: 'ROLE_ADMIN' }
  },
  {
    path: 'student',
    loadChildren: './student/student.module#StudentModule',
    //canActivateChild: [ACLGuard],
    //data: { guard: 'ROLE_ADMIN' }
  },
  {
    path: 'discipline',
    loadChildren: './discipline/discipline.module#DisciplineModule',
    //canActivateChild: [ACLGuard],
    //data: { guard: 'ROLE_ADMIN' }
  },
  {
    path: 'back',
    loadChildren: './back/back.module#BackModule',
    //canActivateChild: [ACLGuard],
    //data: { guard: 'ROLE_ADMIN' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AttandanceRoutingModule { }

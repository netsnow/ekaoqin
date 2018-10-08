import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { UserRoutingModule } from './user-routing.module';
import { UserListComponent } from './list/user-list.component';


const COMPONENTS = [UserListComponent];

@NgModule({
  imports: [
    SharedModule,
    UserRoutingModule
  ],
  declarations: [
    UserListComponent,
  ],
  entryComponents: COMPONENTS
})
export class UserModule { }

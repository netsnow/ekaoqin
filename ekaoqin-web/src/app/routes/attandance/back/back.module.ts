import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { BackRoutingModule } from './back-routing.module';
import { BackRoomComponent } from './room/back-room.component';
import { BackClassComponent } from './class/back-class.component';
import { BackDetailComponent } from './detail/back-detail.component';

const COMPONENTS = [BackRoomComponent];

@NgModule({
  imports: [
    SharedModule,
    BackRoutingModule
  ],
  declarations: [
    BackRoomComponent,
    BackClassComponent,
    BackDetailComponent,
  ],
  entryComponents: COMPONENTS
})
export class BackModule { }

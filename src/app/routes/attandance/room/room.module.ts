import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { RoomRoutingModule } from './room-routing.module';
import { RoomListComponent } from './list/room-list.component';


const COMPONENTS = [RoomListComponent];

@NgModule({
  imports: [
    SharedModule,
    RoomRoutingModule
  ],
  declarations: [
    RoomListComponent,
  ],
  entryComponents: COMPONENTS
})
export class RoomModule { }

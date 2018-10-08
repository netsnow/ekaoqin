import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from '@shared/shared.module';
import { AttandanceRoutingModule } from './attandance-routing.module';


@NgModule({
  imports: [SharedModule, AttandanceRoutingModule],
  declarations: [
    
  ],
})
export class AttandanceModule {}

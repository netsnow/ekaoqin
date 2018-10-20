import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { EntryRoutingModule } from './entry-routing.module';
import { EntryListComponent } from './list/entry-list.component';


const COMPONENTS = [EntryListComponent];

@NgModule({
  imports: [
    SharedModule,
    EntryRoutingModule
  ],
  declarations: [
    EntryListComponent,
  ],
  entryComponents: COMPONENTS
})
export class EntryModule { }

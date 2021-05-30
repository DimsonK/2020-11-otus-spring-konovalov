import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { CurrentUserStoreEffects } from './effects';
import { featureReducer } from './reducer';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    StoreModule.forFeature('current-user', featureReducer),
    EffectsModule.forFeature([CurrentUserStoreEffects]),
  ]
})
export class CurrentUserStoreModule {
}

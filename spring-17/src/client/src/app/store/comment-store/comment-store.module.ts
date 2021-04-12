import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { CommentEffect } from './effects';
import { commentReducer } from './reducer';

@NgModule({
  imports: [
    CommonModule,
    StoreModule.forFeature('comments', commentReducer),
    EffectsModule.forFeature([CommentEffect]),
  ],
  providers: [CommentEffect],
})
export class CommentStoreModule {
}

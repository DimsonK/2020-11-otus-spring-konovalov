import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { AuthorEffect } from './effects';
import { authorReducer } from './reducer';

@NgModule({
  imports: [
    CommonModule,
    StoreModule.forFeature('authors', authorReducer),
    EffectsModule.forFeature([AuthorEffect]),
  ],
  providers: [AuthorEffect],
})
export class AuthorStoreModule {
}

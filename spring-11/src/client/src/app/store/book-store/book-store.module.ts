import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { BookEffect } from './effects';
import { bookReducer } from './reducer';

@NgModule({
  imports: [
    CommonModule,
    StoreModule.forFeature('books', bookReducer),
    EffectsModule.forFeature([BookEffect]),
  ],
  providers: [BookEffect],
})
export class BookStoreModule {
}

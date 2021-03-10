import { Pipe, PipeTransform } from '@angular/core';
import { GenreModel } from '../models/genre.model';

@Pipe({name: 'extractGenreNameProperty'})
export class ExtractGenreNamePropertyPipe implements PipeTransform {

  transform(genres: GenreModel[]): string {
    return genres.map(genre => genre.name).join(', ');
  }

}

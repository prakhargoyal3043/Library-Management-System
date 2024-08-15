import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HashService {

  constructor() { }

  hashit(password:string):string{
    const hash = Array(200).fill(0);
    for (let i = 0; i < password.length; i++) {
      hash[i % hash.length] = (hash[i % hash.length] + password.charCodeAt(i)) % hash.length;
    }
    return hash.join('');
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Hero } from '../hero';
import { HeroService } from '../hero.service';
import { Superpower } from '../superpower';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.scss'],
})
export class HeroDetailComponent implements OnInit {
  hero: Hero | undefined;
  src: String | undefined;
  inputPower = '';
  constructor(
    private route: ActivatedRoute,
    private heroService: HeroService,
    private location: Location
  ) {}

  ngOnInit() {
    this.getHero();
  }

  getHero() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.heroService.getHeroNo404(id).subscribe((hero) => {
      this.hero = hero;
      this.getHeroImg();
    });
  }

  addPower() {
    if (this.inputPower !== '') {
      const id = Number(this.route.snapshot.paramMap.get('id'));

      let power = {} as Superpower;
      power.powerName = this.inputPower;
      this.hero?.superpowers.push(power);

      this.heroService.updateHero(this.hero!).subscribe();
    }
    this.inputPower = '';
  }

  deletePower(power: string) {
    this.hero?.superpowers.forEach((item, index) => {
      if (item.powerName === power) this.hero?.superpowers.splice(index, 1);
    });

    let superpower = {} as Superpower;
    superpower.powerName = power;
    this.heroService.deletePower(superpower, this.hero!.id).subscribe();
  }

  save() {
    if (this.hero) {
      this.heroService
        .updateHero(this.hero)
        .subscribe(() => window.location.reload());
    }
  }

  getHeroImg() {
    if (this.hero) {
      this.heroService.getImageHero(this.hero.name).subscribe((data) => {
        console.log(data.data.results.length);
        this.src = `${data.data.results[0].thumbnail.path}/portrait_xlarge.${data.data.results[0].thumbnail.extension}`;
      });
    }
  }
}

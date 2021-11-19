import { Component } from '@angular/core';

export class Hero {
	id: number;
	name: string;
}

@Component({
	/*template: `
	<h1>{{title}}</h1>
	<h2>{{hero.name}} details!</h2>
	<div><label>id: </label>{{hero.id}}</div>
	<div>
		<label>name: </label>
		<input [(ngModel)]="hero.name" placeholder="name">
	</div>
	`,*/
	templateUrl: '/o/employee-angular-app/lib/app/app.component.html'
})
export class AppComponent {
	hero: Hero = {
		id: 1,
		name: 'Windstorm'
	};
	title = 'Tour of Heroes';
}
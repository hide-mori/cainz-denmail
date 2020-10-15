import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { RenrakuListPage } from './renraku-list.page';

describe('RenrakuListPage', () => {
  let component: RenrakuListPage;
  let fixture: ComponentFixture<RenrakuListPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RenrakuListPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(RenrakuListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { RenrakuDetailPage } from './renraku-detail.page';

describe('RenrakuDetailPage', () => {
  let component: RenrakuDetailPage;
  let fixture: ComponentFixture<RenrakuDetailPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RenrakuDetailPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(RenrakuDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

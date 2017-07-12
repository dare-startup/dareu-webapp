import { DareuWebappPage } from './app.po';

describe('dareu-webapp App', () => {
  let page: DareuWebappPage;

  beforeEach(() => {
    page = new DareuWebappPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});

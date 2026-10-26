"""Device-side smoke check against the current Android emulator. No physical phone changed."""
import subprocess,xml.etree.ElementTree as E,re
from pathlib import Path
root=Path(__file__).resolve().parents[1]/'build'
def adb(*args):return subprocess.check_output(['adb','-e',*args],text=True)
def dump(name):
 adb('shell','uiautomator','dump','/sdcard/orbit-ui.xml')
 adb('pull','/sdcard/orbit-ui.xml',str(root/name))
 return E.parse(root/name)
def tap_text(tree,text):
 node=next(n for n in tree.iter('node') if n.get('text')==text)
 x1,y1,x2,y2=map(int,re.findall(r'\d+',node.get('bounds')))
 adb('shell','input','tap',str((x1+x2)//2),str((y1+y2)//2))
home=dump('smoke-home.xml')
for label in ['Apps','Alerts','Settings']:
 n=next(n for n in home.iter('node') if n.get('text')==label)
 x1,y1,x2,y2=map(int,re.findall(r'\d+',n.get('bounds')))
 assert y2-y1>=96,'Toolbar target below 48dp at 320dpi'
tap_text(home,'Settings')
settings=dump('smoke-settings.xml')
labels=[n.get('content-desc') for n in settings.iter('node')]
assert 'Default home app' in labels
assert 'Toggle cursor' not in labels
assert 'F1 key setup' not in labels
# Swipe the centered panel vertically to reach additional settings.
adb('shell','input','swipe','700','440','700','200','300')
next_page=dump('smoke-settings-next.xml')
assert 'Default home app' not in [n.get('content-desc') for n in next_page.iter('node')]
print('PASS: touch toolbar sizing, settings navigation, non-Kyocera menu filtering and swipe paging')

from PIL import Image
import os
import shutil

source_image_path = '/Users/abrnjic1/.gemini/antigravity/brain/7d24ddb4-a5b2-4ad2-8751-ffc1c17513a0/vopo_logo_play_accent_1784873314799.jpg'
project_dir = '/Users/abrnjic1/Documents/vopoapp.com/app/src/main/res'

if not os.path.exists(source_image_path):
    print(f"Error: Could not find {source_image_path}")
    exit(1)

try:
    img = Image.open(source_image_path).convert('RGB')
    
    # 1. Update app_banner (320x180) - usually TV banner is 320x180
    banner_img = img.resize((320, 180), Image.LANCZOS)
    for dpi in ['drawable', 'drawable-xhdpi']:
        banner_dir = os.path.join(project_dir, dpi)
        if os.path.exists(banner_dir):
            banner_path = os.path.join(banner_dir, 'app_banner.png')
            banner_img.save(banner_path)
            print(f"Saved {banner_path}")
    
    # 2. Update ic_launcher_vault (launcher icons)
    # square icons for different densities
    sizes = {
        'mipmap-mdpi': 48,
        'mipmap-hdpi': 72,
        'mipmap-xhdpi': 96,
        'mipmap-xxhdpi': 144,
        'mipmap-xxxhdpi': 192,
        'mipmap-anydpi-v26': 192 # just overwrite with a normal png if we can't do adaptive icon easily, but adaptive icons are xml. We'll just overwrite the legacy pngs for now.
    }
    
    for folder, size in sizes.items():
        icon_dir = os.path.join(project_dir, folder)
        if os.path.exists(icon_dir):
            icon_img = img.resize((size, size), Image.LANCZOS)
            icon_path = os.path.join(icon_dir, 'ic_launcher_vault.png')
            icon_img.save(icon_path)
            print(f"Saved {icon_path}")
            
    print("Logo updated successfully.")

except Exception as e:
    print(f"Failed: {e}")

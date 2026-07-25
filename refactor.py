import os
import shutil

project_dir = '/Users/abrnjic1/Documents/vopoapp.com'

old_pkg = 'com.streamvault.app'
new_pkg = 'com.vopo.app'

old_path = 'com/streamvault/app'
new_path = 'com/vopo/app'

extensions = {'.kt', '.kts', '.xml', '.pro', '.md', '.properties'}

# 1. Text replacement
for root, dirs, files in os.walk(project_dir):
    if '.git' in root or 'build' in root or '.gradle' in root or ('gradle' in root and 'wrapper' in root):
        continue
    for file in files:
        if any(file.endswith(ext) for ext in extensions):
            filepath = os.path.join(root, file)
            try:
                with open(filepath, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                new_content = content.replace(old_pkg, new_pkg)
                
                # Replace 'StreamVault' with 'VOPO' in XML strings and README
                if file == 'strings.xml' or file == 'README.md' or file == 'CHANGELOG.md':
                    new_content = new_content.replace('StreamVault', 'VOPO')
                    new_content = new_content.replace('streamvault', 'vopo')

                if content != new_content:
                    with open(filepath, 'w', encoding='utf-8') as f:
                        f.write(new_content)
                    print(f"Updated: {filepath}")
            except Exception as e:
                print(f"Error reading {filepath}: {e}")

# 2. Directory structure renaming
src_dirs = [
    'app/src/main/java',
    'app/src/androidTest/java',
    'app/src/test/java',
    'data/src/main/java',
    'data/src/androidTest/java',
    'data/src/test/java',
    'domain/src/main/java',
    'domain/src/test/java',
    'player/src/main/java',
    'player/src/androidTest/java',
    'player/src/test/java'
]

for src_dir in src_dirs:
    full_old_dir = os.path.join(project_dir, src_dir, old_path)
    full_new_dir = os.path.join(project_dir, src_dir, new_path)
    
    if os.path.exists(full_old_dir):
        print(f"Moving {full_old_dir} to {full_new_dir}")
        os.makedirs(os.path.dirname(full_new_dir), exist_ok=True)
        # Create com/vopo
        parent_new_dir = os.path.join(project_dir, src_dir, 'com/vopo')
        os.makedirs(parent_new_dir, exist_ok=True)
        shutil.move(full_old_dir, full_new_dir)
        
        # Clean up old empty dir 'com/streamvault'
        old_parent = os.path.join(project_dir, src_dir, 'com/streamvault')
        if os.path.exists(old_parent) and not os.listdir(old_parent):
            os.rmdir(old_parent)

print("Refactoring complete.")

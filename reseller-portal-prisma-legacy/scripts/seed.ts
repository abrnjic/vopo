import { PrismaClient } from '@prisma/client'
import bcrypt from 'bcryptjs'

const prisma = new PrismaClient()

async function main() {
  const adminEmail = 'admin@vopoapp.com'
  
  const existingAdmin = await prisma.user.findUnique({
    where: { email: adminEmail }
  })

  if (!existingAdmin) {
    const passwordHash = await bcrypt.hash('admin123', 10)
    await prisma.user.create({
      data: {
        email: adminEmail,
        passwordHash,
        role: 'admin',
        status: 'active'
      }
    })
    console.log('Super Admin created: admin@vopoapp.com / admin123')
  } else {
    console.log('Super Admin already exists')
  }
}

main()
  .catch((e) => {
    console.error(e)
    process.exit(1)
  })
  .finally(async () => {
    await prisma.$disconnect()
  })

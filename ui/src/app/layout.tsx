import './globals.css';

export const metadata = {
  title: 'PoS Application',
  description: 'Point of Sale Next.js Client',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}

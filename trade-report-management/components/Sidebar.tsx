import Link from 'next/link';
import {MountainIcon} from '@/components/icons/MountainIcon';
import SidebarLinks from '@/components/SideBarLinks';

export function Sidebar() {
    return (
        <div className="hidden border-r bg-gray-100/40 lg:block dark:bg-gray-800/40">
            <div className="flex h-full max-h-screen flex-col gap-2">
                <div className="flex h-[60px] items-center border-b px-6">
                    <Link className="flex items-center gap-2 font-semibold" href="#">
                        <MountainIcon className="h-6 w-6"/>
                        <span>Tradeheader</span>
                    </Link>
                </div>
                <div className="flex-1 overflow-auto py-2">
                    <nav className="grid items-start px-4 text-sm font-medium">
                        <SidebarLinks/>
                    </nav>
                </div>
            </div>
        </div>
    );
}

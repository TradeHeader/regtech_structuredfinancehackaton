'use client';

import React from 'react';
import Link from 'next/link';
import {usePathname} from 'next/navigation';
import {FileTextIcon} from '@/components/icons/FileTextIcon';
import {BuildingLibraryIcon, DocumentTextIcon} from "@heroicons/react/16/solid";


const links = [
    {label: 'Trades', href: '/trades', Icon: DocumentTextIcon},
    {label: 'Regulations', href: '/regulations', Icon: BuildingLibraryIcon},
    {label: 'Trade Reports', href: '/reports', Icon: FileTextIcon},
];

export default function SidebarLinks() {
    const currentPath = usePathname();

    return (
        <>
            {links.map((link) => (
                <Link
                    key={link.href}
                    href={link.href}
                    className={`flex items-center gap-3 rounded-lg px-3 py-2 transition-all ${
                        link.href === currentPath
                            ? 'text-gray-900 bg-gray-100 dark:bg-gray-800 dark:text-gray-50 hover:text-gray-900 dark:hover:text-gray-50'
                            : 'text-gray-500 dark:text-gray-400 hover:text-gray-900 dark:hover:text-gray-50'
                    }`}
                >
                    <link.Icon className="h-4 w-4"/>
                    {link.label}
                </Link>
            ))}
        </>
    );
}
